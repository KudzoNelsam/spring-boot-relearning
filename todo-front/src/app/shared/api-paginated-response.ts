export interface ApiPaginatedResponse <T> {
  message: string;
  status: number;
  data: T,
  page: number;
  size: number;
  hasPrevious: boolean;
  hasNext: boolean;
  pages: number[];
  totalPage: number;
}
